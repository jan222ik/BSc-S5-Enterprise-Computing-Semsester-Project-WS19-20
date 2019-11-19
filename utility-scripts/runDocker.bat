cd ..
docker build -f Dockerfile -t core .
docker run -p 2345:2345 core
