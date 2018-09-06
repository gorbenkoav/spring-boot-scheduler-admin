import customEndpoint from './custom-endpoint';

SBA.use({
    install({viewRegistry}) {
        viewRegistry.addView({
            name: 'instances/custom',
            parent: 'instances',
            path: 'custom',
            component: customEndpoint,
            label: 'Custom',
            order: 1000,
            isEnabled: ({instance}) => instance.hasEndpoint('health')
        });
    }
});