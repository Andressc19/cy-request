package co.com.pragma.model.loanstatus;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanStatus {
	private Short id;
	private String name;
	private String description;
}
