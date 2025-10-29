Install openjdk:

sudo apt update
sudo apt install openjdk-17-jdk -y

Verify Installation:

java -version

Install Python:

sudo apt update && sudo apt upgrade -y
sudo apt install python3.10 python3.10-venv python3.10-dev -y


Verify Installation

python3 --version

Install pip:

sudo apt install python3-pip -y
pip3 --version


Install node js:

curl -fsSL https://deb.nodesource.com/setup_lts.x | sudo -E bash -
sudo apt install -y nodejs

Verify Installation:

node -v
npm -v

Install yarn:

curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
sudo apt update && sudo apt install yarn -y


Verify Installation:

yarn -v

Install Git:

sudo apt install git -y

Verify Installation:

git --version

Jenkins:

wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null

echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null

sudo apt update
sudo apt install jenkins -y

sudo systemctl enable jenkins
sudo systemctl start jenkins
sudo systemctl status jenkins

Consul:
sudo apt update && sudo apt upgrade -y
sudo apt install unzip curl gnupg lsb-release -y
curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -
sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
sudo apt update
sudo apt install consul -y

sudo tee /etc/consul.d/consul.hcl > /dev/null <<EOF
datacenter = "dc1"
data_dir = "/var/lib/consul"
log_level = "INFO"
node_name = "consul-server"
bind_addr = "0.0.0.0"
client_addr = "0.0.0.0"
ui_config {
  enabled = true
}
server = true
bootstrap_expect = 1
EOF







sudo tee /etc/systemd/system/consul.service > /dev/null <<EOF
[Unit]
Description=HashiCorp Consul
Documentation=https://developer.hashicorp.com/consul/docs
After=network.target

[Service]
User=consul
Group=consul
ExecStart=/usr/local/bin/consul agent -config-dir=/etc/consul.d/
ExecReload=/bin/kill -HUP \$MAINPID
KillMode=process
Restart=on-failure
LimitNOFILE=65536

[Install]
WantedBy=multi-user.target
EOF
