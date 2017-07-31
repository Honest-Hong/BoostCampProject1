package com.project.boostcamp.publiclibrary.data;

import com.project.boostcamp.publiclibrary.util.TimeHelper;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class TestModel {

    public static ArrayList<Estimate> getEstimates() {
        ArrayList<Estimate> datas = new ArrayList<>();
        {
            Estimate e1 = new Estimate();
            e1.setApplierName("홍태준");
            e1.setRestName("한신포차");
            e1.setRestLat(37.401971);
            e1.setRestLng(127.108751);
            e1.setRestImgUrl("http://cfile29.uf.tistory.com/image/2371A64254282923024232");
            e1.setRestStyle("포차");
            e1.setRestMenu("닭발");
            e1.setRestMenuCost(13000);
            e1.setMessage("이쪽으로 오시죠! 저희 가게의 닭발은 모두가 아는 최고의 안주입니다.");
            e1.setWritedTime(TimeHelper.getTime(20, 50));
            e1.setState(Estimate.STATE_CONTACTED);
            datas.add(e1);
        }
        {
            Estimate e1 = new Estimate();
            e1.setApplierName("고정환");
            e1.setRestName("지짐이");
            e1.setRestLat(37.400854);
            e1.setRestLng(127.106535);
            e1.setRestImgUrl("http://cfile29.uf.tistory.com/image/115B080B4CF51DBF2027E6");
            e1.setRestStyle("선술집");
            e1.setRestMenu("볶음");
            e1.setRestMenuCost(11000);
            e1.setMessage("안주가 기가 막힙니다!");
            e1.setWritedTime(TimeHelper.getTime(21, 10));
            e1.setState(Estimate.STATE_WATING);
            datas.add(e1);
        }
        {
            Estimate e1 = new Estimate();
            e1.setApplierName("한운희");
            e1.setRestName("삼구포차");
            e1.setRestLat(37.401991);
            e1.setRestLng(127.106692);
            e1.setRestImgUrl("http://mblogthumb3.phinf.naver.net/20151213_42/esthetic0716_1449994276714R1hWP_JPEG/20151212_203830.jpg?type=w2");
            e1.setRestStyle("포차");
            e1.setRestMenu("계란말이");
            e1.setRestMenuCost(3900);
            e1.setMessage("저렴한 가격에 모시겠습니다~");
            e1.setWritedTime(TimeHelper.getTime(21, 15));
            e1.setState(Estimate.STATE_FAILED);
            datas.add(e1);
        }
        return datas;
    }

    public static ArrayList<Contact> getContacts() {
        ArrayList<Contact> datas = new ArrayList<>();
        {
            Contact c = new Contact();
            c.setApplierName("홍태준");
            c.setApplyNumber(5);
            c.setApplyDate("2017.07.22 21:15");
            c.setApplierPhone("010-5500-0000");
            c.setApplyLat(37.401991);
            c.setApplyLng(127.106692);
            c.setEstimaterName("한신포차");
            c.setEstimaterPhone("031-000-0000");
            c.setEstimateDate("2017.07.22 21:22");
            c.setEstimaterMessage("매운 닭발이 일품입니다. 음료수 서비스로 제공합니다.");
            c.setEstimateLat(37.401971);
            c.setEstimateLng(127.108751);
            c.setContactDate("2017.07.22 21:28");
            datas.add(c);
        }
        return datas;
    }

    public static ArrayList<Apply> getApplies() {
        ArrayList<Apply> datas = new ArrayList<>();
        {
            Apply a = new Apply();
            a.setWriterName("홍태준");
            a.setTitle("분위기 좋고 조용한 회식자리 찾습니다.");
            a.setNumber(5);
            a.setWantedTime(TimeHelper.getTime(19, 50));
            a.setWantedStyle("포차");
            a.setWantedMenu("오돌뼈");
            a.setWantedLatitude(37.401971);
            a.setWantedLongitude(127.108751);
            a.setWritedTime(TimeHelper.getTime(19, 32));
            a.setState(Apply.STATE_APPLY);
            datas.add(a);
        }
        {
            Apply a = new Apply();
            a.setWriterName("고정환");
            a.setTitle("고기 맛이 좋고 친절하신 사장님을 찾습니다.");
            a.setNumber(8);
            a.setWantedTime(TimeHelper.getTime(20, 20));
            a.setWantedStyle("고깃집");
            a.setWantedMenu("삼겹살");
            a.setWantedLatitude(37.401991);
            a.setWantedLongitude(127.106692);
            a.setWritedTime(TimeHelper.getTime(20, 04));
            a.setState(Apply.STATE_APPLY);
            datas.add(a);
        }
        return datas;
    }
}
