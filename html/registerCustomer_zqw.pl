use CGI;
$query  = new CGI;

$name  = $query->param( 'name' );
$passwordd  = $query->param( 'password' );

  # Remove leading and trailing spacing.
  $username =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $username =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $password =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $password =~ s/;|>|>>|<|\*|\?|\&|\|//g;


$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom registerCustomer_zqw ";
$cmd   .= " '". $name . "'";
$cmd   .= " '". $passwordd . "' ";


print( $cmd );
system( $cmd );
#my $referrer = $ENV{HTTP_REFERER};
#print $query->redirect($referrer);



