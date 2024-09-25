from . import db

class VideoInteraction(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    video_id = db.Column(db.String(100))
    user_id = db.Column(db.String(100))
    interaction_type = db.Column(db.String(50))  # like or dislike

    def __init__(self, video_id, user_id, interaction_type):
        self.video_id = video_id
        self.user_id = user_id
        self.interaction_type = interaction_type
