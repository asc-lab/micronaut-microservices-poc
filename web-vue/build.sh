set -e
yarn --cwd web-vue install
yarn --cwd web-vue run build
docker build -t ascmn/web-vue:latest .
