package com.project.boostcamp.firstminiproject.data;

import android.graphics.Bitmap;

/**
 * Created by Hong Tae Joon on 2017-07-04.
 */

public class Timeline {
    // 작성자 이름
    private String writer;
    // 썸네일
    private Bitmap thumb;
    // 내용 텍스트
    private String content;
    // 내용 이미지
    private Bitmap image;
    // 시간
    private String time;
    // 좋아요 수
    private int like;
    // 좋아요 했었음
    private boolean liked;
    // 댓글 수
    private int comment;
    // 공유 수
    private int share;
    // 조회 수
    private int view;
    // 공개한 친구 이름
    private String friendName;
    // 공개 종류
    private int openType;
    // 공개 종류 상수들
    public final static int OPEN_TYPE_LIKE = 0x1000;
    public final static int OPEN_TYPE_SHARE = 0x1001;
    public final static int OPEN_TYPE_COMMENT = 0x1002;
    public final static int OPEN_TYPE_PAGE = 0x1003;
    public final static int OPEN_TYPE_FRIEND = 0x1004;
    // 글 종류
    private int timelineType;
    // 글 종류 상수들
    public final static int TIMELINE_TYPE_TEXT = 0x2000;
    public final static int TIMELINE_TYPE_IMAGE = 0x2001;
    public final static int TIMELINE_TYPE_TEXT_AND_IMAGE = 0x2002;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public int getOpenType() {
        return openType;
    }

    public void setOpenType(int openType) {
        this.openType = openType;
    }

    public int getTimelineType() {
        return timelineType;
    }

    public void setTimelineType(int timelineType) {
        this.timelineType = timelineType;
    }
}
