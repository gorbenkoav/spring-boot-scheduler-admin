import schedulerAdminEndpoint from './scheduler-admin-endpoint';

SBA.use({
    install({viewRegistry}) {
        viewRegistry.addView({
            name: 'instances/scheduler-admin',
            parent: 'instances',
            path: 'scheduler-admin',
            component: schedulerAdminEndpoint,
            label: 'Scheduler Admin',
            order: 1000,
            isEnabled: ({instance}) => instance.hasEndpoint('health')
        });
    }
});