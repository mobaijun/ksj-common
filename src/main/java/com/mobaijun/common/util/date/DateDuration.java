/*
 * Copyright (C) 2022 [www.mobaijun.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobaijun.common.util.date;

import java.time.temporal.ChronoUnit;

/**
 * software：IntelliJ IDEA 2022.2.3<br>
 * class name: DateDuration<br>
 * class description: 日期时间
 *
 * @author MoBaiJun 2022/11/23 13:53
 */
public final class DateDuration {

    /**
     * MILLIS_1000
     */
    public static final long MILLIS_1000 = 1000;

    /**
     * WEEK_MILLIS
     */
    public static final long WEEK_MILLIS = ChronoUnit.WEEKS.getDuration().getSeconds() * MILLIS_1000;

    /**
     * DAY_OF_MONTH_MILLIS
     */
    public static final long DAY_OF_MONTH_MILLIS = ChronoUnit.DAYS.getDuration().getSeconds() * MILLIS_1000;

    /**
     * HOUR_MILLIS
     */
    public static final long HOUR_MILLIS = ChronoUnit.HOURS.getDuration().getSeconds() * MILLIS_1000;

    /**
     * MINUTE_MILLIS
     */
    public static final long MINUTE_MILLIS = ChronoUnit.MINUTES.getDuration().getSeconds() * MILLIS_1000;

    /**
     * SECOND_MILLIS
     */
    public static final long SECOND_MILLIS = ChronoUnit.MINUTES.getDuration().getSeconds() * MILLIS_1000;
}
