package com.kks.multiviewholder

data class TabItem(var name: String, var number: Int, var type : Int){
    companion object {
        const val GROUP_TYPE = 0
        const val CONTENT_TYPE = 1
    }
}
