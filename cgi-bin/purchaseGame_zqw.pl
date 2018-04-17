use CGI;
$query  = new CGI;

$titles  = $query->param( 'titles' );
$quantity  = $query->param( 'quantity' );
$name  = $query->param( 'name' );



  # Remove leading and trailing spacing.
  $titles =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $titles =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $quantity =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $quantity =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $name =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $name =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom purchaseGame_zqw";
$cmd   .= " '". $titles . "'";
$cmd   .= " '". $quantity . "'";
$cmd   .= " '". $name . "'";


#print( $cmd );
system( $cmd );




