package com.bluepearlbiotech.patient.domain;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import com.bluepearlbiotech.patient.web.rest.TestUtil;

public class LookupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lookup.class);
        Lookup lookup1 = new Lookup();
        lookup1.setId("00000000-0000-0000-0000-000000000001");
        Lookup lookup2 = new Lookup();
        lookup2.setId(lookup1.getId());
        assertThat(lookup1).isEqualTo(lookup2);
        lookup2.setId("00000000-0000-0000-0000-000000000002");
        assertThat(lookup1).isNotEqualTo(lookup2);
        lookup1.setId(null);
        assertThat(lookup1).isNotEqualTo(lookup2);
    }
}
