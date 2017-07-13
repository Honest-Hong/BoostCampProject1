package com.project.boostcamp.secondminiproject;

import java.util.ArrayList;

/**
 * Created by Hong Tae Joon on 2017-07-11.
 */

public class ShopTestData {
    public static ArrayList<Shop> get() {
        ArrayList<Shop> shops = new ArrayList<>();
        {
            Shop s = new Shop();
            s.setImageUrl("http://cfile6.uf.tistory.com/image/235DC2505893CADA27F869");
            s.setName("미스사이공");
            s.setChecked(false);
            s.setText("저렴한 가격에 쌀국수를 맛 볼 수 있는 식당입니다. 시원한 육수 국물이 맛있습니다");
            s.setDistance(100);
            s.setLike(59);
            s.setTime(10001000);
            shops.add(s);
        }
        {
            Shop s = new Shop();
            s.setImageUrl("https://i0.wp.com/www.venturesquare.net/wp-content/uploads/2017/04/170503-mcdonald-001.png?resize=702%2C307");
            s.setName("맥도날드");
            s.setChecked(false);
            s.setText("하루에 약 5,400만명의 고객이 찾고 있는 세계에서 가장 널리 알려진 체인음식점이며, 햄버거 연쇄음식점으로는 가장 규모가 크다. 맥도날드는 주로 햄버거, 치킨류, 아침 메뉴, 디저트류를 팔고 있다.");
            s.setDistance(150);
            s.setLike(101);
            s.setTime(10002010);
            shops.add(s);
        }
        {
            Shop s = new Shop();
            s.setImageUrl("https://d2t7cq5f1ua57i.cloudfront.net/images/r_images/56060/50526/56060_50526_86_0_645_2016121420494646.jpg");
            s.setName("미정국수 0410");
            s.setChecked(true);
            s.setText("백종원의 프랜차이즈점 중 하나로 저렴한 국수를 제공하는 식당입니다. 세트를 주문하면 덮밥과 함께 작은 국수가 나와 배부르게 먹을 수 있습니다.");
            s.setDistance(200);
            s.setLike(64);
            s.setTime(10000500);
            shops.add(s);
        }
        {
            Shop s = new Shop();
            s.setImageUrl("https://d2t7cq5f1ua57i.cloudfront.net/images/r_images/57469/56474/57469_56474_80_0_4203_20151113131243367.jpg");
            s.setName("담소사골순대");
            s.setChecked(false);
            s.setText("사골 국물로 만든 순대국밥 식당입니다. 포장을 하면 300원이 더 저렴합니다.");
            s.setDistance(120);
            s.setLike(34);
            s.setTime(10001800);
            shops.add(s);
        }
        return shops;
    }
}
