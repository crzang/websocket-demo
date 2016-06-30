package pro.crzang.service;

/**
 * Created by crzang on 30.06.16.
 */
/*
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {
  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostConstruct
  protected void initialize() {
    save(new Account("user@mail.ru", "demo"));
    save(new Account("admin@host.com", "admin"));
  }

  @Transactional
  public Account save(Account account) {
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    accountRepository.save(account);
    return account;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findOneByEmail(username);
    if (account == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return createUser(account);
  }

  public void signin(Account account) {
    SecurityContextHolder.getContext().setAuthentication(authenticate(account));
  }
  private Authentication authenticate(Account account) {
    return new UsernamePasswordAuthenticationToken(createUser(account), null,
        Collections.singleton(createAuthority(account)));
  }

  private User createUser(Account account) {
    return new User(account.getEmail(), account.getPassword(),
        Collections.singleton(createAuthority(account)));
  }

  private GrantedAuthority createAuthority(Account account) {
    return new SimpleGrantedAuthority(account.getRole());
  }
}*/
