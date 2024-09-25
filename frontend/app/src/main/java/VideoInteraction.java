public class VideoInteraction {
    private String video_id;
    private String user_id;
    private String interaction_type;

    public VideoInteraction(String video_id, String user_id, String interaction_type) {
        this.video_id = video_id;
        this.user_id = user_id;
        this.interaction_type = interaction_type;
    }
}
