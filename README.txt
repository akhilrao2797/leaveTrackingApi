1.  URL -   /employee/
    Method - get
    Response Json format -   {
                            "employeeId": 10,
                            "gender": "MALE",
                            "joiningDate": "2017-02-22",
                            "balanceLeaves": 3,
                            "takenOptionalLeave": false,
                            "startLeaveDate": null,
                            "endLeaveDate": null,
                            "compOff": 1,
                            "compOffValidMonth": 2,
                            "noOfChildren": 0,
                            "onMaternityOrPaternityLeave": false,
                            "maternityLeave": 0,
                            "paternityLeave": 0
                        }



2.  URL -     /leave/apply/
    Method -   post
    Input Json format -    {
                            "employeeId": 12,
                            "startDate":"2019-02-25",
                            "endDate": "2019-02-26",
                            "type":"OutOfOffice",
                            "option":"blanketCoverage"
                        }

3.   URL - /employee/<id>/leavehistory/
    Method -   get
    Return Type - ArrayList
                    []

4.  URL- /employee/<id>/workedhours/
    Method -  get
    Response Json format -   {
                                    "employeeId":10,
                                    "startDateTime":"2019-02-22T06:21:09.740",
                                    "endDateTime": "2019-02-22T16:21:09.740"
                                  }

5.  URL- /employee/<id>/workedhours/
    Method -  post
    Return Type - string
    Input Json format -   {
                        "employeeId":10,
                        "startDateTime":"2019-02-22T06:21:09.740",
                        "endDateTime": "2019-02-22T16:21:09.740"
                      }


6.  URL- /employee/<id>/leavebalance/
    Method -  get
    Return type -  string
                    "Leave Balance for Employee Id : 10  =  3 days"


7.  URL- /employee/<id>/compoffbalance/
    Method -  get
    Return type- string
                "CompOff Balance for Employee Id : 10  is  1 days"



8.  URL -   /employee/
    Method - post
    Input Json format -   {
                            "employeeId": 10,
                            "gender": "MALE",
                            "joiningDate": "2017-02-22",
                            "balanceLeaves": 3,
                            "takenOptionalLeave": false,
                            "startLeaveDate": null,
                            "endLeaveDate": null,
                            "compOff": 1,
                            "compOffValidMonth": 2,
                            "noOfChildren": 0,
                            "onMaternityOrPaternityLeave": false,
                            "maternityLeave": 0,
                            "paternityLeave": 0
                        }
9.  URL -   /employee/
    Method - delete
    Input Json format -   {
                            "employeeId": 10,
                            "gender": "MALE",
                            "joiningDate": "2017-02-22",
                            "balanceLeaves": 3,
                            "takenOptionalLeave": false,
                            "startLeaveDate": null,
                            "endLeaveDate": null,
                            "compOff": 1,
                            "compOffValidMonth": 2,
                            "noOfChildren": 0,
                            "onMaternityOrPaternityLeave": false,
                            "maternityLeave": 0,
                            "paternityLeave": 0
                        }
10.  URL -   /employee/
    Method - put
    Input Json format -   {
                            "employeeId": 10,
                            "gender": "MALE",
                            "joiningDate": "2017-02-22",
                            "balanceLeaves": 3,
                            "takenOptionalLeave": false,
                            "startLeaveDate": null,
                            "endLeaveDate": null,
                            "compOff": 1,
                            "compOffValidMonth": 2,
                            "noOfChildren": 0,
                            "onMaternityOrPaternityLeave": false,
                            "maternityLeave": 0,
                            "paternityLeave": 0
                        }

11.  URL - /employee/<id>/
     Method - get
     Response  Json format - {
                                 "employeeId": 10,
                                 "gender": "MALE",
                                 "joiningDate": "2017-02-22",
                                 "balanceLeaves": 3,
                                 "takenOptionalLeave": false,
                                 "startLeaveDate": null,
                                 "endLeaveDate": null,
                                 "compOff": 1,
                                 "compOffValidMonth": 2,
                                 "noOfChildren": 0,
                                 "onMaternityOrPaternityLeave": false,
                                 "maternityLeave": 0,
                                 "paternityLeave": 0
                             }
