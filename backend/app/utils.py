import boto3
from botocore.exceptions import NoCredentialsError
from flask import current_app as app

def generate_presigned_url(video_key):
    s3_client = boto3.client('s3', 
                              aws_access_key_id=app.config['AWS_ACCESS_KEY_ID'], 
                              aws_secret_access_key=app.config['AWS_SECRET_ACCESS_KEY'], 
                              region_name=app.config['AWS_REGION'])

    try:
        response = s3_client.generate_presigned_url('get_object',
                                                    Params={'Bucket': app.config['S3_BUCKET_NAME'], 'Key': video_key},
                                                    ExpiresIn=3600)
    except NoCredentialsError:
        return None
    return response
