package com.robotlab.expeditions2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class PdfFile {
    @PrimaryKey(autoGenerate = true)
    private int pdfId;
    private String pdfFileUrl;
    private String pdfName;
    private String pdfTitle;
    private String pdfFileSize;
    private int expeditionId;
    private int downloadId;
    private boolean status;

    public PdfFile() {
    }

    @Ignore
    public PdfFile(int pdfId, String pdfFileUrl, String pdfName, String pdfTitle, String pdfFileSize, int expeditionId) {
        this.pdfId = pdfId;
        this.pdfFileUrl = pdfFileUrl;
        this.pdfName = pdfName;
        this.pdfTitle = pdfTitle;
        this.pdfFileSize = pdfFileSize;
        this.expeditionId = expeditionId;
    }

    public int getPdfId() {
        return pdfId;
    }

    public void setPdfId(int pdfId) {
        this.pdfId = pdfId;
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

    public int getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
