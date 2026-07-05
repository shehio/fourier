# fourier

Small Java implementations of Fourier-related transforms, written in 2016.
This is an **archival repo** — the code is kept as a record of early coursework/practice
and is not maintained or intended for production use.

## Contents

All sources live in [`default/`](default/) (no package declaration; classes compile into the default package).

| File | What it does |
|---|---|
| `Complex.java` | Immutable complex-number class (arithmetic, conjugate, exp/sin/cos/tan, magnitude, phase). Adapted from a [Sanfoundry example](http://www.sanfoundry.com/java-program-compute-discrete-fast-fourier-transform-approach/). |
| `DFT.java` | Direct O(n²) discrete Fourier transform. The `forward` flag selects forward vs. inverse; the inverse divides each term by n. |
| `FFT.java` | Recursive radix-2 Cooley–Tukey FFT. Input is zero-padded to the next power of two. |
| `IFFT.java` | Inverse FFT via the conjugate trick: conjugate → FFT → conjugate → scale by 1/n. |
| `DCT.java` | Discrete cosine transform computed by mirroring the input (reverse-and-concatenate) and running the FFT on the doubled sequence. |
| `PSD.java` | Power spectral density: per-bin squared magnitude of a complex spectrum. |

Each transform class has a `main` method with a small demo (typically the sequence 0..4,
transformed and inverse-transformed back).

## Running

No build system — plain `javac`:

```sh
cd default
javac *.java
java DFT   # or FFT, IFFT, DCT
```

## Known quirks (left as-is on purpose)

- `FFT.calculate` prints intermediate results at every recursion level — leftover
  debug output, so the demos are noisy.
- The FFT twiddle factor uses the e^{+2πikt/n} sign convention, while `DFT` conjugates
  for the forward transform; the two forward transforms therefore differ by a sign
  convention in the imaginary part.
- Zero-padding to a power of two means outputs for non-power-of-two inputs are the
  transform of the padded sequence, not the exact-length transform.
