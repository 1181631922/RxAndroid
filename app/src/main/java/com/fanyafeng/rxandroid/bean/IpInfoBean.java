package com.fanyafeng.rxandroid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by 365rili on 16/6/16.
 * <p>
 * {
 * "code": 0,
 * "data": {
 * "country": "美国",
 * "country_id": "US",
 * "area": "",
 * "area_id": "",
 * "region": "",
 * "region_id": "",
 * "city": "",
 * "city_id": "",
 * "county": "",
 * "county_id": "",
 * "isp": "",
 * "isp_id": "",
 * "ip": "63.223.108.42"
 * }
 * }
 */
public class IpInfoBean implements Parcelable{
    public String country;
    public String country_id;
    public String area;
    public String area_id;
    public String region;
    public String region_id;
    public String city;
    public String city_id;
    public String county;
    public String county_id;
    public String isp;
    public String isp_id;
    public String ip;

    public IpInfoBean() {
    }

    protected IpInfoBean(Parcel in) {
        country = in.readString();
        country_id = in.readString();
        area = in.readString();
        area_id = in.readString();
        region = in.readString();
        region_id = in.readString();
        city = in.readString();
        city_id = in.readString();
        county = in.readString();
        county_id = in.readString();
        isp = in.readString();
        isp_id = in.readString();
        ip = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(country_id);
        dest.writeString(area);
        dest.writeString(area_id);
        dest.writeString(region);
        dest.writeString(region_id);
        dest.writeString(city);
        dest.writeString(city_id);
        dest.writeString(county);
        dest.writeString(county_id);
        dest.writeString(isp);
        dest.writeString(isp_id);
        dest.writeString(ip);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IpInfoBean> CREATOR = new Creator<IpInfoBean>() {
        @Override
        public IpInfoBean createFromParcel(Parcel in) {
            return new IpInfoBean(in);
        }

        @Override
        public IpInfoBean[] newArray(int size) {
            return new IpInfoBean[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIsp_id() {
        return isp_id;
    }

    public void setIsp_id(String isp_id) {
        this.isp_id = isp_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "IpInfoBean{" +
                "country='" + country + '\'' +
                ", country_id='" + country_id + '\'' +
                ", area='" + area + '\'' +
                ", area_id='" + area_id + '\'' +
                ", region='" + region + '\'' +
                ", region_id='" + region_id + '\'' +
                ", city='" + city + '\'' +
                ", city_id='" + city_id + '\'' +
                ", county='" + county + '\'' +
                ", county_id='" + county_id + '\'' +
                ", isp='" + isp + '\'' +
                ", isp_id='" + isp_id + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
