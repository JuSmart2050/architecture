package com.shanian.arch.mvvm.bean;

import java.util.List;

public class ActorDetailBean {


    /**
     * mobile_url : https://movie.douban.com/celebrity/1274242/mobile
     * name : 黄渤
     * name_en : Bo Huang
     * gender : 男
     * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.jpg"}
     * summary : 　　黄渤不但具有专业的表演素质，在他身上更散发着来自内心的真实与质朴。他曾经做过歌手、节目主持人和舞蹈编导，后来又经过了专业的配音学习，这些丰富的人生经历，使他更具备了优于其他演艺新人的专业素质。
     * 　　黄渤毕业于北京电影学院配音专业。在就读电影学院之前就参加过管虎导演的作品演出，包括大家熟悉的电视电影《上车，走吧》、电视剧《黑洞》。其中《上车，走吧》不但获得了该年度的金鸡奖最佳电视电影奖，黄渤更凭片中的出色演出，受到了业内人士及广大观众对其演技的认可。《生存之民工》是黄渤与导演管虎的第三次合作，两人已默契十足，管虎在黄渤身上发掘出更深层的潜质。剧中他朴实、自然的表演受到了导演和前辈演员们的大力肯定。他还曾为《海底总动员》、《加勒比海盗》、《绿巨人》、《谁陷害了兔子罗杰》等大片做中文配音。
     * 　　2006年，黄渤出演的两部电影都在暑期上映，一部是《新街口》，另一部是《疯狂的石头》，后者受到票房和口碑的双重好评，而黄渤主演的黑皮更是博得大多数观众的喝彩，在电影方面可以说上了一个新的台阶。
     * 　　之后黄渤作品不断，两部《大电影》、《大灌篮》、与香港电影人合作的《每当变幻时》、《高兴》等等，都见证着黄渤演技的日益精进。2009年黄渤更是一口气推出《疯狂的赛车》、《斗牛》、《铁人》、《倔强萝卜》等多部影片，风头无两。并凭借在《斗牛》中的精彩表现，获得第46届台湾电影金马奖影帝桂冠。
     * photos : [{"thumb":"https://img1.doubanio.com/view/photo/m/public/p2220782208.jpg","image":"https://img1.doubanio.com/view/photo/l/public/p2220782208.jpg","cover":"https://img1.doubanio.com/view/photo/sqs/public/p2220782208.jpg","alt":"https://movie.douban.com/celebrity/1274242/photo/2220782208/","id":"2220782208","icon":"https://img1.doubanio.com/view/photo/s/public/p2220782208.jpg"}]
     * birthday : 1974-08-26
     * alt : https://movie.douban.com/celebrity/1274242/
     * born_place : 中国,山东,青岛
     * constellation : 处女座
     * id : 1274242
     */

    private String mobile_url;
    private String name;
    private String name_en;
    private String gender;
    private AvatarsBean avatars;
    private String summary;
    private String birthday;
    private String alt;
    private String born_place;
    private String constellation;
    private String id;
    private List<PhotosBean> photos;

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getBorn_place() {
        return born_place;
    }

    public void setBorn_place(String born_place) {
        this.born_place = born_place;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }
}
