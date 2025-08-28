package co.com.pragma.model.loanstatus;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanStatus {
	private Long id;
	private String name;
	private String description;
}
