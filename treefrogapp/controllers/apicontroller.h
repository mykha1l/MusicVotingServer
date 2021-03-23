#ifndef APICONTROLLER_H
#define APICONTROLLER_H

#include "applicationcontroller.h"


class T_CONTROLLER_EXPORT ApiController : public ApplicationController
{
    Q_OBJECT
public:
    ApiController() : ApplicationController() { }

public slots:
    void action();
};

#endif // APICONTROLLER_H
