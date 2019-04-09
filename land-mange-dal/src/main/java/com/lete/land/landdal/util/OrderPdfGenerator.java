/*
 * Copyright (c) 2018. Industics Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lete.land.landdal.util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * 订单pdf生成
 *        OrderPdfGenerator pdfGenerator = new OrderPdfGenerator(templateEngine.process("purchase_order",context));
 *
 *         byte[] finalBytes = pdfGenerator.generateBytes();
 * @date 2019-03-05 16:02
 */
public class OrderPdfGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPdfGenerator.class);
    private BaseFont BASE_FONT;
    private Font FONT_09;
    private String orderContent;
    private String headerContent = null;
    private String footerContent = null;

    public OrderPdfGenerator(String orderContent) {
        this.orderContent = orderContent;
        BASE_FONT = null;
        try {
            BASE_FONT = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            LOGGER.warn("Create Font exception: {}", ExceptionUtils.getMessage(e));
        }
        FONT_09 = new Font(BASE_FONT, 9f);
    }

    public byte[] generateBytes() {
        return generateOrderByte(orderContent);
    }


    private byte[] generateOrderByte(String strContent) {
        try (ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream()) {
            Document document = new Document(new RectangleReadOnly(842, 595));
            document.setMargins(36, 36, 54, 54);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, pdfOutputStream);
            document.open();

            if (StringUtils.isNotBlank(headerContent)) {
                setTableHeader(pdfWriter, FONT_09, headerContent);
            }

            if (StringUtils.isNotBlank(footerContent)) {
                setTableFooter(pdfWriter, FONT_09, footerContent);
            }

            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new ByteArrayInputStream(strContent.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8, new XMLWorkerFontProvider() {
                @Override
                public Font getFont(final String fontname, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color) {
                    Font font = new Font(BASE_FONT, size, style, color);
                    font.setColor(color);
                    return font;
                }
            });

            document.close();
            return pdfOutputStream.toByteArray();
        } catch (DocumentException | IOException e) {
            LOGGER.warn("Generate PDF exception: {}", ExceptionUtils.getMessage(e));
        }
        return null;
    }

    /**
     * 设置页眉
     */
    private void setTableHeader(PdfWriter writer, Font font09, String content) {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(520f);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(1);
        Paragraph p = new Paragraph(content, font09);
        cell.setPaddingLeft(10f);
        cell.setPaddingTop(-20f);
        cell.addElement(p);
        table.addCell(cell);
        Header event = new Header(table);
        writer.setPageEvent(event);
    }

    /**
     * 页脚是文字
     */
    private void setTableFooter(PdfWriter writer, Font font09, String content) {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(520f);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(1);
        Paragraph p = new Paragraph(content, font09);
        cell.setPaddingLeft(10f);
        cell.setPaddingTop(-2f);
        cell.addElement(p);
        table.addCell(cell);
        Footer event = new Footer(table);
        writer.setPageEvent(event);
    }

    //页眉事件
    private class Header extends PdfPageEventHelper {
        PdfPTable header;

        Header(PdfPTable header) {
            this.header = header;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            //把页眉表格定位
            header.writeSelectedRows(0, -1, 38, 810, writer.getDirectContent());
        }
    }

    //页脚事件
    private class Footer extends PdfPageEventHelper {
        PdfPTable footer;

        Footer(PdfPTable footer) {
            this.footer = footer;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            //把页脚表格定位
            footer.writeSelectedRows(0, -1, 38, 50, writer.getDirectContent());
        }
    }

    public String getHeaderContent() {
        return headerContent;
    }

    public void setHeaderContent(String headerContent) {
        this.headerContent = headerContent;
    }

    public String getFooterContent() {
        return footerContent;
    }

    public void setFooterContent(String footerContent) {
        this.footerContent = footerContent;
    }
}
