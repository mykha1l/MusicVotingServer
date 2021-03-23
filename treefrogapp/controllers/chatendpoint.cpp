#include "chatendpoint.h"

#define TOPIC "main"

ChatEndpoint::ChatEndpoint() :
    ApplicationEndpoint()
{ }

ChatEndpoint::ChatEndpoint(const ChatEndpoint &) :
    ApplicationEndpoint()
{ }

bool ChatEndpoint::onOpen(const TSession &)
{
    subscribe(TOPIC);
    publish(TOPIC, QStringLiteral(" [ New person joined ]\n"));
    return true;
}

void ChatEndpoint::onClose(int)
{
    unsubscribe(TOPIC);
    publish(TOPIC, QStringLiteral(" [ A person left ]\n"));
}

void ChatEndpoint::onTextReceived(const QString &text)
{
    publish(TOPIC, text);
}

void ChatEndpoint::onBinaryReceived(const QByteArray &)
{ }


// Don't remove below this line
T_DEFINE_CONTROLLER(ChatEndpoint)
