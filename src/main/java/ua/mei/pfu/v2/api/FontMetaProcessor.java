package ua.mei.pfu.v2.api;

import ua.mei.pfu.v2.api.provider.BaseFontProvider;

public interface FontMetaProcessor<T extends BaseFontProvider> {
    T createProvider();
}
