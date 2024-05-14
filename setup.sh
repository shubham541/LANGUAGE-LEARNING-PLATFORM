IFS='-'
for path in app-commons auth-service resource-service
do
	cd "$path"
	echo "inside -> $path"
	mvn clean install -DskipTests
	if test -f Dockerfile; then
		echo "building docker image"
		docker build . -t  "language-learning/${path}:latest"
	fi
	cd ..
done

cd language-platform-ui
echo "inside -> language-platform-ui"
npm install
npm run build
docker build . -t language-learning/app-ui:latest
cd ..
