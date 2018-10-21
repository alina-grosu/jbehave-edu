Scenario: zapara
Meta:
@skip

Given tests cann run
When foo equals <foo>
Then bar equals <bar>
And baz equal <baz>
And bob equal <beb>
And fiq equal to <fiq>

Examples:
|foo|bar|baz                                       |beb                                 |fiq                                                                                                                                            |
|1  |2  |stories\expect\json\zapara.story\baz1.json|{omg:OMG1, number:100, bigdex:100.2}|{bar: {"omg": "OMG3", "number": 103, "bigdex": 100.3}, "foo": {"baz": "baz3", "beb": "beb3", "foq": "feq3"} , "strings": ["foo", "bar", "baz"]}|
|3  |4  |{baz:baz2, beb:beb2, foq:feq2}            |{omg:OMG1, number:100, bigdex:100.2}|{bar: {"omg": "OMG4", "number": 104, "bigdex": 100.4}, "foo": {"baz": "baz4", "beb": "beb4", "foq": "feq4"}, "strings": ["fo", "ba", "ba"]}    |

Scenario: zapara_date
Given tests cann run
And date is <date>

Examples:
|date                                      |
|{"dt":"2018-10-20 23:30:33"}              |
|{"d":"2018-10-20", "t":"23:30:33"}        |
|{dt:now}                                  |
|{d:now, t:now}                            |
|{d:now, t:"23:59:59"}                     |
|{d:"2018-10-20", t:now}                   |
|{d:"2018-10-20"}                          |
|{dt:now, mo:-1, day:last}                 |
|{dt:now, mo:1, day:first}                 |
|{dt:now, mo:-2, day:-3, y:-4, h:-5, mi:10}|
|{dt:"2018-08-20 23:30:33", mo:-2, day:-3, y:-4, h:-5, mi:10}|