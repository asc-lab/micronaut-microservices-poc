gcloud container clusters create micronaut \
    --machine-type n1-standard-2 \
    --num-nodes 2 \
    --zone europe-west3-a \
    --scopes "https://www.googleapis.com/auth/projecthosting,storage-rw"
