# mocha
To avoid multiple rapid clicks on Android's Views with a single annotation "```@GuardClick```"
```java
button.setOnClickListener(new View.OnClickListener() {
    @GuardClick
    public void onClick(View v) {
        mClickCount++;
        showClickCount(clickCountTextView);
    }
});
```

This project uses [AspectJ][AspectJ] to weave code. That will change the client code's line numbers and make it hard to debug. 
See alternative project: [clickguard][clickguard].


# License

    Copyright 2015 Feng Dai

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



[AspectJ]:https://github.com/eclipse/org.aspectj
[clickguard]:https://github.com/fengdai/clickguard
