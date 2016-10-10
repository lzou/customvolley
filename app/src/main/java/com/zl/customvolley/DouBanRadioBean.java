package com.zl.customvolley;

import java.util.List;

/**
 * Created by zoulu on 16/10/10.
 * 用GsonFormat自动生成
 * 豆瓣音乐模型类
 */

public class DouBanRadioBean {


    /**
     * name_en : Personal Radio
     * seq_id : 0
     * abbr_en : My
     * name : 私人兆赫
     * channel_id : 0
     */

    private List<ChannelsBean> channels;

    public List<ChannelsBean> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelsBean> channels) {
        this.channels = channels;
    }

    public static class ChannelsBean {
        private String name_en;
        private int seq_id;
        private String abbr_en;
        private String name;
        private int channel_id;

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public int getSeq_id() {
            return seq_id;
        }

        public void setSeq_id(int seq_id) {
            this.seq_id = seq_id;
        }

        public String getAbbr_en() {
            return abbr_en;
        }

        public void setAbbr_en(String abbr_en) {
            this.abbr_en = abbr_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
        }
    }
}
