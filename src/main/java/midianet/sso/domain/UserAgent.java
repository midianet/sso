package midianet.sso.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAgent {
    private String client;
    private String browser;
    private String so;
    private String ip;
}
