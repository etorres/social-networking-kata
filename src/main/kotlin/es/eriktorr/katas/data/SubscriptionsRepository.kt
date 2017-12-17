package es.eriktorr.katas.data

import es.eriktorr.katas.subscriptions.SubscriptionsFinder
import es.eriktorr.katas.subscriptions.SubscriptionsRecorder

class SubscriptionsRepository: SubscriptionsRecorder, SubscriptionsFinder {

    override fun subscribe(userName: String, followedUserName: String) {
        TODO("not implemented")
    }

    override fun subscriptionsOf(userName: String): List<String> {
        TODO("not implemented")
    }

}