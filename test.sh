sudo apt-get update
echo 'y' | sudo apt install openjdk-8-jdk
sudo apt-get update
echo 'y' | sudo apt-get install maven
sudo rm -rf /opt/code/
sudo mkdir /opt/code
sudo chmod 777 code/ -R
cd /opt/code/
sudo gsutil cp gs://mobopan-dev/download/opt/code/mobopanserver.tar.gz .
sudo tar zxvf mobopanserver.tar.gz
cd /opt
sudo chmod 775 code/ -R
cd /opt/code/mobopanserver/
cd /opt/code/mobopanserver/MobopanProcessor/target
sudo cp -r MobopanProcessor-1.0-SNAPSHOT.jar MobopanProcessor-lanunch.jar
cd /opt/code/mobopanserver/
fuser -k 8080/tcp
nohup java -jar -Xms256m -Xmx256m /opt/code/mobopanserver/MobopanProcessor/target/MobopanProcessor-lanunch.jar>/opt/code/javalog.log 2>&1 &