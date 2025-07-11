package com.butterknifebudgeting.app

class DatabaseManager {
    lateinit var boxStore: BoxStore
        private set

    fun initDatabase(): BoxStore {
        val dbDir = File(System.getProperty("user.home"), ".yourapp-server/objectbox")
        dbDir.mkdirs()

        boxStore = MyObjectBox.builder()
            .directory(dbDir)
            .build()

        return boxStore
    }

    fun closeDatabase() {
        if (::boxStore.isInitialized) {
            boxStore.close()
        }
    }
}

class UserRepository(private val databaseManager: DatabaseManager) {
    private val userBox = databaseManager.boxStore.boxFor<User>()

    fun getAllUsers(): List<User> = userBox.all

    fun createUser(user: User): User {
        val id = userBox.put(user)
        return userBox.get(id)
    }

    fun getUserById(id: Long): User? = userBox.get(id)

    fun updateUser(user: User): User {
        userBox.put(user)
        return user
    }

    fun deleteUser(id: Long): Boolean = userBox.remove(id)
}