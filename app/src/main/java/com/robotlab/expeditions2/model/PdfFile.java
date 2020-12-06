package com.robotlab.expeditions2.model;

import androidx.room.Ignore;

public class PdfFile {

    private String pdfFileUrl;
    private String pdfName;
    private String pdfTitle;
    private String pdfFileSize;
    private int expeditionId;
    private int downloadId;
    private boolean status;

    @Ignore
    public PdfFile(String pdfFileUrl, String pdfName, String pdfTitle, String pdfFileSize, int expeditionId) {
        this.pdfFileUrl = pdfFileUrl;
        this.pdfName = pdfName;
        this.pdfTitle = pdfTitle;
        this.pdfFileSize = pdfFileSize;
        this.expeditionId = expeditionId;
    }

    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfTitle() {
        return pdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public String getPdfFileSize() {
        return pdfFileSize;
    }

    public void setPdfFileSize(String pdfFileSize) {
        this.pdfFileSize = pdfFileSize;
    }

    public int getExpeditionId() {
        return expeditionId;
    }

    public void setExpeditionId(int expeditionId) {
        this.expeditionId = expeditionId;
    }
}
