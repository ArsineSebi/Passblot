@Library('jenkins-libs@passbolt') _

import lib.Passbolt

node() {
    try {
        stage('Checkout') {
            checkout scm
        }

        stage('passbolt') {
            String server_address_credentials_id = 'passbolt-server-url'
            String user_password_credentials_id = 'passbolt-user-password'
            String privatekey_file_credentials_id = 'passbolt-private-key'
            def passbolt = new Passbolt(this)
            passbolt.init("0.1.7", server_address_credentials_id, user_password_credentials_id, privatekey_file_credentials_id)
            passbolt.getResource('9f7713ea-d813-4645-848a-f4b39d105a69')
            passbolt.listResources()
        }

    } finally {
        stage('Cleanup') {
            step([$class: 'WsCleanup'])
            deleteDir()
        }
    }
}
