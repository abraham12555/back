package la.kosmos.app



class DeleteUserSessionJob {
    def userService

    static triggers = {
        //Password expiration job executed daily at 00:00
        cron name:'deleteUserSessionJob', cronExpression: '0 0 0 1/1 * ? *'
    }

    def execute() {
        userService.deleteUserSessions()
    }
}
