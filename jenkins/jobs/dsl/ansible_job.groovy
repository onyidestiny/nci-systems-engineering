
job('ansible_deploy') {
    description('Ansible deploy')

    wrappers {
        colorizeOutput()
        preBuildCleanup()
    }

    parameters {
        stringParam('GIT_REPO', 'https://github.com/CBIIT/nci-systems-engineering.git')
        stringParam('GIT_BRANCH', 'master')       
        stringParam('ANSIBLE_INVENTORY', 'inventory/env/hosts', 'Ansible inventory file')
        stringParam('ANSIBLE_TAGS', '', 'Ansible tags')       
        stringParam('ANSIBLE_LIMITS', '', 'Ansible limits') 
        stringParam('ANSIBLE_USER', 'deploy', 'Ansible user')
        stringParam('ANSIBLE_EXTRA_VARS', '', 'Ansible extra vars')
    }

    environmentVariables {
        keepSystemVariables(true)
        keepBuildVariables(true)
    }

            scm {
                git {
                    remote {
                        url('https://github.com/CBIIT/nci-systems-engineering.git')
                    }
                    branches('master')
                }
            }

    steps {
        shell(readFileFromWorkspace('jenkins/jobs/shell/ansible_deploy.sh'))
    }
}
