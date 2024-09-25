from flask import jsonify, request
from . import db
from .models import VideoInteraction
from .utils import generate_presigned_url
from flask import current_app as app

@app.route('/interact', methods=['POST'])
def interact_and_get_next_video():
    data = request.json
    video_id = data.get('video_id')
    user_id = data.get('user_id')
    interaction_type = data.get('interaction_type')
    
    # Save interaction
    interaction = VideoInteraction(video_id, user_id, interaction_type)
    db.session.add(interaction)
    db.session.commit()

    # Fetch the next video (you could add logic to choose the next video)
    next_video_key = "videos/next_video.mp4"
    
    # Generate a pre-signed URL for the next video from S3
    video_url = generate_presigned_url(next_video_key)
    
    if video_url:
        return jsonify({"message": "Interaction saved", "next_video_url": video_url}), 200
    else:
        return jsonify({"error": "Failed to generate video URL"}), 500
