package com.fanyafeng.rxandroid.hong9.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 365rili on 16/6/21.
 */
public class BannerBean {

    public String state;

    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<Banner> banner = new ArrayList<>();

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }
    }

    public static class Banner {
        public int id;
        public String img;
        public String url;
        public String plainUrl;
        public String caption;
        public int type;
        public int vorder;
        public String shareTitle;
        public String shareDesc;
        public String shareCircle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPlainUrl() {
            return plainUrl;
        }

        public void setPlainUrl(String plainUrl) {
            this.plainUrl = plainUrl;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVorder() {
            return vorder;
        }

        public void setVorder(int vorder) {
            this.vorder = vorder;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getShareDesc() {
            return shareDesc;
        }

        public void setShareDesc(String shareDesc) {
            this.shareDesc = shareDesc;
        }

        public String getShareCircle() {
            return shareCircle;
        }

        public void setShareCircle(String shareCircle) {
            this.shareCircle = shareCircle;
        }

        @Override
        public String toString() {
            return "BannerBean{" +
                    "id=" + id +
                    ", img='" + img + '\'' +
                    ", url='" + url + '\'' +
                    ", plainUrl='" + plainUrl + '\'' +
                    ", caption='" + caption + '\'' +
                    ", type=" + type +
                    ", vorder=" + vorder +
                    ", shareTitle='" + shareTitle + '\'' +
                    ", shareDesc='" + shareDesc + '\'' +
                    ", shareCircle='" + shareCircle + '\'' +
                    '}';
        }
    }
}
