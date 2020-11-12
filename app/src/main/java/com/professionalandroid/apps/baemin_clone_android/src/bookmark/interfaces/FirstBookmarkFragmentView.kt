package com.professionalandroid.apps.baemin_clone_android.src.bookmark.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.bookmark.models.BookmarkPageResponse

interface FirstBookmarkFragmentView {
    fun getBookmark(body: BookmarkPageResponse)
    fun bookmarkIsEmpty()
}