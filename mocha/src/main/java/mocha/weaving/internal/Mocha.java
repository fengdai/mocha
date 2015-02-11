/*
 * Copyright 2015 Feng Dai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mocha.weaving.internal;

import android.os.Handler;
import android.os.Looper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Mocha {
    private static final String POINTCUT_ON_CLICK_METHOD
            = "execution(void android.view.View.OnClickListener+.onClick(android.view.View))";
    private static final String POINTCUT_ON_ITEM_CLICK_METHOD
            = "execution(void android.widget.AdapterView.OnItemClickListener+.onItemClick(android.widget.AdapterView, android.view.View, int, long))";

    private static final int GUARDING = 0;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    @Pointcut(POINTCUT_ON_CLICK_METHOD + " || " + POINTCUT_ON_ITEM_CLICK_METHOD)
    public void clickMethod() {
    }

    @Pointcut("clickMethod() && execution(@mocha.weaving.GuardClick * *(..))")
    public void annotatedClickMethod() {
    }

    @Pointcut("clickMethod() && within(@mocha.weaving.GuardClick *)")
    public void clickMethodInsideAnnotatedType() {
    }

    @Around("annotatedClickMethod() || clickMethodInsideAnnotatedType()")
    public Object guard(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        if (!HANDLER.hasMessages(GUARDING)) {
            result = joinPoint.proceed();
            HANDLER.sendEmptyMessageDelayed(GUARDING, 600);
        }
        return result;
    }
}
