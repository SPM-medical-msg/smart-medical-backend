{
    path: '/index-chat',
    component: () => import(/* webpackChunkName: "dashboard" */ '../views/Chat/ChatContent.vue'),
    meta: { title: '医生管理' ,hasPerms: false},
    hasPerms: false,
    children:[
        {
            path: '',
            component: () => import(/* webpackChunkName: "dashboard" */ '../views/Chat/SingleChat.vue'),
            meta: { title: '单聊管理' ,hasPerms: false},
            hasPerms: false,
        },
        {
            path: 'friend-apply',
            component: () => import(/* webpackChunkName: "dashboard" */ '../views/Chat/FriendApply.vue'),
            meta: { title: '好友申请' ,hasPerms: false},
            hasPerms: false,
        },
        {
            path: 'group',
            component: () => import(/* webpackChunkName: "dashboard" */ '../views/Chat/GroupChat.vue'),
            meta: { title: '好友申请' ,hasPerms: false},
            hasPerms: false,
        },
        // {
        //     path: 'file-info',
        //     component: () => import(/* webpackChunkName: "dashboard" */ '../views/Chat/FileInfo.vue'),
        //     meta: { title: '文件信息' ,hasPerms: false},
        //     hasPerms: false,
        // },
    ]
},

    "mitt": "^3.0.1",
    "mqtt": "^4.3.7",