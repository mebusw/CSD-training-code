package refactor.banking

import spock.lang.*


@Title("向指定账号存款")
@Narrative("""关于测试的大段文本描述""")
class BankingSpockTest extends spock.lang.Specification {
    interface IOne {
        def foo(int i)
    }

    class One implements IOne {
        def foo(int i) {
            return i
        }
    }

    @Issue(["问题#23", "问题#34"])
    def "test deposit"() {
        given: "arrange"
        def one = new One()
        int a = one.foo(1);

        when: "act"
        a += 1

        then: "assert"
        2 == a

    }

    def "test mock"() {
        given: "arrange"
        def one = Mock(IOne) {
            foo(_) >> 3355
        }

        int a = one.foo(1);

        when: "act"
        a += 1

        then: "assert"
        3356 == a

    }

}


