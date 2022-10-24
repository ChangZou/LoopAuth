module.exports = {
    head: [
        ['link', { rel: 'icon', href: '/logo.png' }]
    ],
    title: 'LoopAuth',
    description: '精简、轻量、更细粒度的Java权限框架',
    themeConfig: {
        nav: [
            { text: '首页', link: '/' },
            { text: '文档', link: '/doc/' },
            { text: '交流群', link: '/doc/#交流群' },
            { text: '行为准则', link: '/behavior/' },
            { text: 'Gitee', link: 'https://gitee.com/lucky-color/loop-auth' },
            { text: 'GitHub', link: 'https://github.com/ChangZou/LoopAuth' },
        ],
        sidebar: [
            {
                title: '写在前面的骚话',    // 标题
                collapsable: false,   // 展开状态
                sidebarDepth: 2,  // 目录深度
                children: [   // 子导航
                    '/doc/',
                    '/doc/preamble/contribute',
                    '/doc/preamble/cite',
                    '/doc/preamble/version',
                ]
            },
            {
                title: '不看骚话,直接上手',
                sidebarDepth: 2,
                children: [
                    '/doc/start/spring',
                    '/doc/start/globalTool',
                    '/doc/start/forcedOffline',
                    '/doc/start/auth',
                    '/doc/start/exception',
                    '/doc/start/redis',
                    '/doc/start/abac',
                ],
                collapsable: false,
            },
            {
                title: '其他框架的使用',
                sidebarDepth: 2,
                children: [
                    '/doc/otherstarter/webflux',
                ],
                collapsable: false,
            },
            {
                title: '正经内容',
                sidebarDepth: 2,
                children: [
                    '/doc/context/userSession'
                ],
                collapsable: false,
            },
            {
                title: '如何单独使用某模块',
                sidebarDepth: 2,
                children: [
                    '/doc/separate/'
                ],
                collapsable: false,
            }
        ],
        
    }
}