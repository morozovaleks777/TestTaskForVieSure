package com.morozov.testtaskforviesure.navigation



fun NavigationManager.goBack() {
    send(NavigationAction.GoBack)
}

fun NavigationManager.goToBooksDetail(bookDetail: BookDetail) {
    goTo(bookDetail)
}

fun NavigationManager.goTo(direction: NavigationObject) {
    send(NavigationAction.GoTo(direction = direction))
}
