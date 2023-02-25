package com.raisetech.work09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class NameServiceImplTest {
    @InjectMocks
    NameServiceImpl nameServiceImpl;
    @Mock
    NameMapper nameMapper;

    @Test
    public void 存在するユーザーIDを指定したときに正常にユーザーが返されること() throws Exception {
        doReturn(Optional.of(new Name("koyama"))).when(nameMapper).findById(1);
        //findById(1)実行時、必ずid:1, name:yoshihito koyama　を返す

        Name actual = nameServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Name("koyama"));
    }

    @Test
    public void 存在しないユーザーIDを指定したときに期待通り例外を返すこと() throws Exception {
        doThrow(new ResourceNotFoundException("例外です")).when(nameMapper).findById(0);
        Throwable throwable = catchThrowable(() -> nameServiceImpl.findById(0));
        /*catchThrowableを使うことで、与えられたコードブロックを実行し、
        その中でスローされた例外をキャッチして返す、若しくは例外がスローされなかったらnullを返す
        */
        assertThat(throwable).isInstanceOf(ResourceNotFoundException.class).hasMessage("例外です");
        /*assertThatThrownByで試したが、アサーションエラーが発生した。
        同メソッドが期待するような形式で例外をラップできていなかった可能性があり、
        isInstanceOfメソッドを使用して、スローされた例外が期待通りの型であることを確認することになった。
         */
        assertThat(throwable.getCause()).isNull();
        /*ResourceNotFoundExceptionは通常別の例外が原因となってスローされるケースがあるので
        その原因となる別の例外が存在しないことを検証している。
        その別の例外が存在する場合、getCause()はその例外を返すため、isNull()は失敗する*/
    }
}
