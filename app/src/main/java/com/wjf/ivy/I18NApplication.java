package com.wjf.ivy;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class I18NApplication extends TinkerApplication {
    public I18NApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.wjf.ivy.I18NApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

}
