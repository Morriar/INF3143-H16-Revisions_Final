/*
 * Copyright 2015 Alexandre Terrasa <alexandre@moz-code.org>.
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
package ex1;

import java.util.ArrayList;
import java.util.List;

public class Range {

    int from;
    int to;

    public Range(int from, int to) {
        assert from <= to;
        this.from = from;
        this.to = to;
    }

    public List<Integer> getValues() {
        ArrayList<Integer> res = new ArrayList<>();
        while (from <= to) {
            res.add(from);
            from++;
        }
        return res;
    }

}
