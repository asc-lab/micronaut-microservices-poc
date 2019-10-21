yarn --cwd web-vue install
[ $? -eq 0 ] || exit 1
yarn --cwd web-vue run build
[ $? -eq 0 ] || exit 1