package com.tomweb.Entity

class Role {

    String name

    String authority

    Date dateCreated = new Date()
    Date lastUpdated = new Date()
    Long createdBy = 0
    Long updatedBy = 0

}
