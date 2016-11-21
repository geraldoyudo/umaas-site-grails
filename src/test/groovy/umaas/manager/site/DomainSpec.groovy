package umaas.manager.site

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Domain)
class DomainSpec extends HibernateSpec  {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
      Domain d = new Domain();
      d.userId = 13223;
      d.code= "test1212"
      d.name = "1234"
      d.save();
      print d.domainId;

}
